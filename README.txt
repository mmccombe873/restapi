A short collection of inputs has been included in the file PalindromeCollection.  This can be run using 
the Postman application. This is to demonstrate that the app handles all inputs correctly. 
After running this, results.txt should read: 

"michael,civic,true
michael,madam,true
mike,civil,false" 

Spring Boot was used to build this web application. The app takes a request via the header in the format
http://localhost:8080/username/data. Using route mapping, it then creates a GET request in the 
PalindromeController class to extract the username and data. This class also records inputs in the cache 
and in the file results.txt. Upon startup, the cache is populated with the data from results.txt in the 
CacheInitialiser class. If the input data is already in the cache, the app gets the stored response from 
the cache rather than checking it again, to improve performance. If the input is new, the app calls the 
checkPalindrome method in the PalindromeService class which reverses the string and compares it to the 
original string. This class also validates the input and rejects inputs with spaces, numbers, or 
punctuation. The API returns a response in JSON format, including a boolean named Palindrome which 
indicates whether the input is a palindrome or not. I assumed that repeat values should not be added to 
the cache even if the username is different, so the program will still retrieve the existing response 
from the cache in this scenario. The logs will display the user who originally added the data to the cache. 

The app architecture follows the OOP paradigm, with object classes being used to create request and
response objects. This improves modularity, allows this code to be reused, and makes testing easier.
Unit testing has been included in the test folder to show correct functionality of the application.