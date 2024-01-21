# Rimer-As3

NOTES: when searching for a location,please use the search icon from the phone virtual keyboard for it to work.

Things that were updated after the presentation:
- Instead of the user choose a desire driver, the user is randomly assign to a driver
- Driver flow: continued to move the driver to the trip details after ride confirmation, driver will navigated to the trip detail and will able to finished ride by pressing the confirm finish ride button
- ride cancel will have a status of "decline" instead of being deleted. 
- User flow: after click the find driver button, will be navigate to the waiting page where the page will listen to the booking status every five second. if a driver accepted a trip, the status will change to "accepted". if the ride is finished with the status of "completed", user will be direct to a user review where the user can leave a review about the driver. 
- UI: Update various activities's UI
- Add activities and UI: DriverInformationActivity, DriverListActivity, UserListActivity, HistoryActivity
- Various of backend fixes and improvements

Things that are unable to do:
- choose driver based on money they made compare to other or maybe other factor.
- more payment methods
- Implement edit username, email, password
- Abilities to add more information about the driver for better authenticate and security
- Add Super user to manage the application
- Implement back-end to list user, driver, ride history for super user.

Technology used:
- Front-end: Java, android SDK, Gradle, Google Play Services (Maps, Places, Location, Auth), Retrofit(Type-safe HTTP client for Android and Java), Retrofit Converter.
- Retrofit is used to communicate with backend via REST API.
- Backend: Node.js, Express.js, Mongoose, Mongodb, Heroku, Postman.
- Node.js, Express.js, Mongoose: these are used to create a server to build REST API and its routes.
- Postman is used for testing all of the api route and make test request to the database.
- Heroku is used to host the project server and make the REST API available on the internet.

Information about work's contribution:
Vo Khai Minh (Team leader) - s3879953:
    => Design application flow, dataflow, screen flow
- frontend: 
    => User-Home view, 
    => user and driver trip details view, 
    => Driver sign up view, 
    => user and driver confirmation view, 
    => User home side menu.
    => Create Review and User controllers 
    => Update Booking controllers for Driver flow
    => Responsible for anything driver related.
    => Responsible for anything user related after the user find a destination.
- Backend:
    => Setup express server, mongo database
    => Host REST API on Heroku (with Ha Giang)
    => Create role-base authentication
    => Design data Collections for User, Review
    => create logic for CRUD request 
    
Nguyen Thi Ha Giang - s3914108:
- frontend: 
    => Search map
    => Get distance between pick up point and destination
    => Fix booking view
    => Form for user edit information
- Backend:
    => Login by Google account
    => CRUD booking

Nguyen Tuan Thang - s3877039:
- frontend:
    => login and signup screen
    => controller for login
    => fix code for driver UI.
    => history booking screen
    => userlist and driverlist screen

Nguyen Tan Truong - S3754703:
- frontend:
    => Add, update driver homepage view, fix circular shape
    => Add, update booking view, fix circular shape
    => Add, update driver information view, add account setting button

Hoang Duc Anh -s3847506: 
- frontend:
    => add, update trip-detail screen, 
    => add, update on going-screen display_user_information



Team contribution evaluation:

Vo Khai Minh (Team leader) - s3879953: 126%
Nguyen Thi Ha Giang - s3914108: 126%
Nguyen Tuan Thang - s3877039: 126%
Nguyen Tan Truong - S3754703: 60%
Hoang Duc Anh -s3847506: 60%