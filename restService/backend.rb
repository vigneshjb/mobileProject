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
	result = "<html><head><title> RESQUEBOT - " + device_id + "</title></head><body>"

	File.readlines("#{device_id}.log").each do |line|
		result += "<p>#{line}</p>"
	end
	result += "</body></html>"
	result
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
			f.puts("#{result['timestamp'].to_s}:#{result['description'].to_s}")
		end
	end
end