require 'sinatra'
require 'json'

get '/healthcheck' do
  "Hello World!"
end

post '/logs' do
	begin 
		request_body = JSON.parse(request.body.read)
		result_set = request_body['logs']
		device_id = request_body['deviceID']
	rescue
		status 400
		return {:status=>"FAILED", :message=>"JSON parsing error"}.to_json
	end

	if ( result_set == nil || device_id == nil)
		status 400
		return {:status=>"FAILED", :message=>"Logs/Device Id was not found"}.to_json
	else
		savelogs(result_set, device_id)
		status 200
		return {:status=>"SUCCESS", :message=>"Logs Recorded"}.to_json
	end
end

get '/logs/:device_id' do
	device_id = params['device_id']
	unless (File.exist?("#{device_id}.log"))
		status 400
		return {:status=>"FAILED", :message=>"Device Id was not found"}.to_json
	end
	result_html = "<html><head><link rel='stylesheet' href='http://www.public.asu.edu/" +
		"~vjayabal/css/bootstrap.min.css'><title> RESQUEBOT - " + device_id + "</title></head><body>" +
		"<h3 class = 'text-center'> Logs collected from "+ device_id + "</h3>" +
		"<table class='table table-striped table-condensed table-hover table-bordered' style='width: 700px; margin: 0 auto;'>" +
		"<tr><th class='text-right col-md-4'>TIMESTAMP</th><th>DESCRIPTION</th></tr>"

	File.readlines("#{device_id}.log").each do |line|
		result_html += "<tr><td class='text-right'>#{line.split('$').first}</td><td>#{line.split('$').last}</td>"
	end
	result_html += "</table></body></html>"
	result_html
end

delete '/logs/:device_id' do
	device_id = params['device_id']
	unless (File.exist?("#{device_id}.log"))
		status 400
		return {:status=>"FAILED", :message=>"Device Id was not found"}.to_json
	end
	File.delete("#{device_id}.log")
	return {:status=>"SUCCESS", :message=>"Logs Deleted"}.to_json
end

def savelogs(result_set, device_id)
	File.open("#{device_id}.log","a+") do |f|
		result_set.each do |result|
			f.puts("#{result['timestamp'].to_s}$#{result['description'].to_s}")
		end
	end
end