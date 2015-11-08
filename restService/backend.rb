require 'sinatra'
require 'json'

get '/healthcheck' do
  "Hello World!"
end

post '/logs' do
	begin 
		result_set = JSON.parse(request.body.read)['logs']
	rescue
		status 400
		return '{"status":"FAILED", "message":"Logs not found"}'
	end

	if ( result_set == nil )
		status 400
		return '{"status":"FAILED", "message":"Logs not found"}'
	else
		savelogs(Time.now, result_set)
		status 200
		return '{"status":"SUCCESS", "message":"Logs Recorded"}'
	end
end

def savelogs(time, result_set)
	File.open(time.to_s,"w+") do |f|
		result_set.each do |result|
			f.puts(result.to_s)
		end
	end
end