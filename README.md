# send-email-service

# How to build project
git clone https://github.com/BarunKrSingh/send-email-service.git
* cd send-email-service
* mvn clean install

# How to run/deploy application
java -jar target\send-email-service.jar -Xmx2048M -Xss256K -Xmx2048m -XX:MaxPermSize=512m

#Format of the message on RabbitMQ
<msg>
 <se>barunkumar.singh321@gmail.com</se>
 <re>barun.singh1204@gmail.com</re>
 <sb>Testing Rabbit Email Service</sb>
 <bd>HEy, your email service is working fine</bd>
</msg> 