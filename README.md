# Rimer-As3

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