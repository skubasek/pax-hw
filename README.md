# pax-hw

------------------
Challenge #1
------------------
Source location:
/src/main/java/com.sk.hw_1.message

Requirements:
Service is a spring-boot app. To run, you need
maven and java installed on the machine. 

RUNNING:
clone git repo
from the main directory, execute
mmvn spring-boot:run

Once started, you should see:
 Started Hw1Application in 2.554 seconds (JVM running for 7.318)

Once started, you can execute CURL requests, ie:
http://localhost:8080/messages/abc

You can also validate the app is doing what is
supposed to by looking at the tests in /src/main/test

PERFORMANCE
As app would acquire more and more users, it will consume
more and more memory. To scale, and to maintain a good SLA,
instead of using an in-memory map, a datastore like memcached can be used.

------------------
Challenge #2
------------------
Source location:
/src/main/java/com.sk.hw_1.message

RUNNING:
From pax-hw, execute "mmvn clean install"

Then change directory to: ~/pax-hw/target/classes 
Execute:
java -cp . com.sk.hw_2.price.PairFinder 2000
Candy Bar 500, Headphones 1400

You can update prices.txt (and re-run)

Big O: 
N - number of items

parsing file - O(N)
finding pair - O(N^2)
So overall: 0(N^2)

------------------
Challenge #3
------------------
N - length of string
M - number of Xs

RUNNING:
From pax-hw directory (see challenge 2), you can execute:
java -cp . com.sk.hw_3.xo.XoPrinter XX
00
01
10
11

Big O: 
finding location: 0(N)
incrementing: O(M^2)
Overall: O(M^2) 
