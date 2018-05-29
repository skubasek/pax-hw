# pax-hw

Challenge #1

Service is a spring-boot app. To run, you need
maven and java installed on the machine. 

Source location:
/src/main/java/com.sk.hw_1.message

Step #1 - start the app

It should take a few seconds to start.
Once started, you can execute CURL requests.

You can also validate the app is doing what is
supposed to by looking at the tests in /src/main/test

PERFORMANCE
As app would acquire more and more users, it will consume
more and more memory. To scale, and to maintain a good SLA,
instead of using an in-memory map, memcached can be used.

Challenge #2
Source location:
/src/main/java/com.sk.hw_1.message

How to run:

Big O: 
N - number of items

parsing file - O(N)
finding pair - O(N^2)
So overall: 0(N^2)

CHALLENGE #3
N - length of string
M - number of Xs

Big O: 
finding location: 0(N)
incrementing: O(M^2)
Overall: O(M^2) 
