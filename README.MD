This project is a very lightweight webserver

Example:
```

//Port and Max Thread(pool)
WebServer webServer = new WebServer(8080, 4);
webServer.addListener(responseManager -> {

     responseManager.addHttpData("<h1>Hello World!</h1>");
     responseManager.addHttpData("<h3 style='color: red;'>Colored Txt</>");
     responseManager.addHttpData("<p>This project is a very lightweight webserver</p>");
     responseManager.addHttpData("<p>Your ip adress:"+responseManager.getRequestAdress()+"</>");


  // responseManager.setCancelled(true);
});

webServer.start();
```


Soon to be added (Coming soon):
- Sending different pages with root
- Sending img etc files
- Send Javascript
- Restful service publishing
- Send JSON
- Mini IOC Container


I am doing this project to better understand the working logic of other frameworks and to develop more in this field by engineering from scratch. 