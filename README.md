## MVP on Android: how to organize the presentation layer ##

The MVP (Model View Presenter) is a derivative of the known MVC (Model View Controller), which for a while now is gaining importance in the development of the Android applications. There are more people talking about it, but yet very little reliable and structured information available.

###What is the MVP?###
The MVP pattern allows you to **separate the presentation layer logic**, so that everything related to what the app does (business logic) is separated from what is represented on he screen. Ideally with the MVP pattern you would achieve the same logic but have totally different views and interchangeable.

The first thing to be clear is that the MVP is a architectural pattern, which means it is only responsible for the presentation layer.

###Why use MVP?###
In Android we have a problem arising from the fact that Android activities are closely coupled to both the mechanical interface as well as data access. So much that there are CursorAdapter classes that mix adapters, which are part of the view, with the cursor, something that should be relegated to the depths of the data access layer.
For an application to be easily extensible and maintainable it needs well separated layers. What do we do if tomorrow instead of pulling database we need to do a service on the network? We would have to redo the entire view.
The MVP has the view independent from that data. We divided the application in at least three distinct layers, and may also test each independently.

###How to implement MVP on Android?###
Well this is where it all starts to become more diffuse. There are many variations of MVP and each can adjust the pattern to their needs and the way you feel most comfortable. The pattern varies mainly depending on the amount of responsibility that we delegate to the presenter.
Is the view the on who should activate or deactivate a progress bar, or should that be handled in the presenter? And who decides which buttons should be painted in the Action Bar? That's where the tough decisions begin. I will show how I usually work, but I want this article to be more a forum for discussion that strict guidelines on how to apply the MVP, because today there is no standardized way to implement it.


**The host**

The presenter is responsible to act intermediary between the view and model . Retrieves data model and returns the formatted view. But unlike the typical MVC, also it decides what happens when you interact with the view.


**The view**

The view is usually implemented by Activity (although it may be a fragment ... depending on how the app is structured), which contains reference to the presenter. Ideally the presenter it's injected in the activity by Dagger, but in the case of not using it, the view is responsible for creating the presenter object. The only sight that will be calling a method of the presenter each time an action on the interface, usually pressed a button, a list item, etc.


**The model**

In an application with a good layered architecture, this model would be only the gateway to the domain layer and business logic. If we were using the clean architecture of Uncle Bob, surely the model would be an interactor to implement a use case. For now it is enough to see it as the data provider you want to show in the view.


**This example**

      Main Project
      ├── Api Lib
      │     ├── <some stuff from the lib>
      │     └── library
      │         ├── AndroidManifest.xml
      │         ├── res
      │         └── src // -> contains the Retrofit Java interfaces, also information about our backend url
      │ 
      ├── Lib Commons
      │     └── <same structure as the lib 1>
      |         └──  // -> contains the app Models, simple POJOs for rest communication
      │ 
      └── App folder
            └── AndroidManifest.xml
            └── res
            └── src  // -> the sample app usage
                 ├── dagger
                 |     ├── components // -> dagger components
                 |     └── modules // -> dependency injection modules used to provide activity scope context and satisfy activity/fragment dependency needs
                 ├── domain // -> contains the interactors and implementation for the network communication
                 └── ui // -> contains the activityes and presenters


###Conclution###
The logic interface in Android is not easy, but with the Model-View-Presenter pattern becomes a little easier to prevent our activities end up being highly coupled classes hundreds or even thousands of lines. In large applications it becomes necessary to organize well our code if we want no maintenance and expansion becomes impossible.
