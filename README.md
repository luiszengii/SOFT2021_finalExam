# Apply for a simple extention for the API key issue

**This application reached DISTINCTION level of implementation**

**Please take the lastest commit as the marked one**

# Instructions
## CLI(Deprecated)
Type in `gradle run --args="firstArgument secondArgument"` in the terminal at the project directory.
The first argument defines whether to use online/offline version of input API, and the second one is for output API. 

After gradle run, the system will ask the user to input a valid US Zip code and an api key. If given zip code has
been requested before and stored in local database, the system would than ask the user whether to use the cached
data or fetch new one from the API.  

Then the system will print out the weather data required and will proceed with asking if the user wish to send the
 data to certain email. If user input is `Y` the system would ask for further information needed to complete the
  sending emails.
  
## GUI
To run the program with GUI, simple type `gradle run` and the GUI will pop up.  
Please select the mode for both api, and type in threshold in valid range before continue.  
Weather data must been obtained from input API first to send any email through output API.  
Please type in the currect API key otherwise the return data would be null or failed response code.

## Dummy APIs
Both Dummy API handler has default return data and only accepts certain parameters.  
#### Dummy input API handler
A Dummy input API handler only accept requesting for uszip code "28546" and "28547", the first one will return
 weather data without any alert, but the later one return data with alert.  
 It only accept private API key: fb6d55b649db4857b569c84977bad72b
#### Dummy output API handler
This api key only cares about if the api key is: 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  
And ignore the other data input, and return a response code 202. 

## red on dummy api get current weather feature
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/a295e02f3ba98355cdcf36959c76f057f75ac520

## green 
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/aaff58f171f5506c2496c67d4a7d8cb785278900

## red for creating input api handler
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/d8d52f6f763c54f2d4959a7f0b605f49dcf4e76e

## green 
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/e9d8cc668cec7d400cf8f83ff32708a45e21c426

## red for output api handler
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/3ca6e33a8d6dfcf59d8ecb0ab6894eb0ae18767c

## green 
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/ba27617733c9f48a0853ef4b3316f450ce972cb3

## red for database
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/9e3cedeefc96fdd74bb4bf6b0805d523447f8698

## green 
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/82ea7f93d505dca03e4f446824cf15f06f6ebddb

## red for introducing threshold in application.java
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/81b3995df35f552aabe4b619137448ba0e61ba00

## green 
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/9c5c3d408a935498dee5a502ae0f061c037c32a6

## red for adding current data variable in application
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/5d936401390faeeb785c63c769f321bb8ab1c5ed

## green 
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/da6af78471cfcb9b9ff38b125f720ac76b83d954

## red for application determine if zip code is over heat
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/971fda4e5f2e493334a07c061cdd34bae82aa2a6

## green 
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/ef5a248693b0b393b4c350a671b9a084901fd156

## red for application to manipulate weather data(*)
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/de34654f0f5934b9d21fc8f0f7defa35cbd9fbd5

## green 
https://github.sydney.edu.au/lzen9557/SCD2_2021_Exam/commit/22385cb86e91ff3178dce08368462830cf0f69d0
