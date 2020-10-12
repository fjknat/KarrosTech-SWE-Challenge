----------------------------------------------------------------------
Guide to run:
-------------------------------------------------------------
- Read file \document\Manual Unit test.xlsx to view manual test and guides to run Restful API
- Check on \document folder to view My Workflow Diagram.
- This project support run on Docker with Dockerfile
- Project have 3 APIs:
	+ Upload GPS file : http://localhost/gps/upload
	+ Get latest tracks list: http://localhost/gps/latest
	+ Get track detail: http://localhost/gps/track?trackId=1
- You can run the Unit test with JUnit
----------------------------------------------------------------------
My TODO list:
----------------------------------------------------------------------
- Implement how to draw maps with GPS data and export base64 format for Latest Tracks API. 
- Improve GSP file parse, smart caching with list favorite GPS.
- Authentication and authorization with Spring security
- Why we don't use Time Series DB as InfluxDB for GPS data.
With real-time or near real-time we can store as regular time series.
With the GPX file, we can store as event data.
If We want to real-time check with device or event ( as marathon monitoring) we can use the Asynchronous Communication framework as (Kafka, RabbitMQ) to provide data in 2 way are 
	+ InfluxDB store and analyze data
	+ firebase or RethinkDB to view or check.
- Focus to store GPS values, some unimportant data which we can store to document DB as MongoDB or Elasticsearch


----------------------------------------------------------------------



You have an idea develop a website which allow users to store and share GPS track online (similar to http://www.trackprofiler.com/track/index). After discussion with your team, they helped you to came up with some mock-up files. 
Front-end side will be developed by another team member. You are the only one who is going to be in charge of the backend service development.

Because you are so excited to show the idea to your CEO, you decided to reduce the scope and focus one developing 1 WS API with three endpoints:

- An endpoint that allow users to upload "gpx" file and store mandatory information such as "metadata, waypoint, track" 
- An endpoint to return a list of "Latest track" from our users
- An endpoint to allow users to view details of their gpx file

Although this is a prototype version, but you are a professional software engineer. You don't allow yourself to code without a System Diagram or Workflow Diagram, or produce "dirty-code" and code without Unit Tests. Additionally, since this is a fairly small and simple project, you are not allowed to use the Lombok library.

Once your have completed your solution, please upload them to Github.

This is all you have right now: 

- https://en.wikipedia.org/wiki/GPS_Exchange_Format
- Mock-up files
- A sample gpx file
- A passionate heart, if you don't like the given mock-up files, feel free to change and show your CEO a better version
- Your team is a big fan of "Spring IO" tech stack, so they prefers you use Sprint Boot as a starting point
- An in-memory database is enough for this moment (H2)




