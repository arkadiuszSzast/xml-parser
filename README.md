Project is dockerized.
To run it just use a docker-compose file in the main directory. 
The image is pushed to the repository but if needed a dockerfile is also included.

1. Run using docker-compose
  - Move to the root directory of the project and type docker-compose up
  The image will be downloaded and application starts on port 8080
  
2. Run using docker image
  - Move to the root directory of the project.
  - Build the image using docker build -t xml-parser .
  - Run the image using docker run -p 8080:8080 xml-parser
