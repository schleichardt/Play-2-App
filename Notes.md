# SBT
* use `~test` in the play console to run the tests on every source code change

# Ebean

* Model.Finder ... findRowCount() for count queries

# IntelliJ IDEA

* use the sbt console with the command `run` to start the play server
* after renaming or moving a controller class the routes have to be adjusted manually and the task `clean` must be called
* external dependencies have to be configured manually?

# Typical mistakes and errors

* wrong imports: the package play.api ist for Scala developers, play for Java developers
* Firefox WebDriver Plugin is outdated and does not work on the current Firefox: https://groups.google.com/forum/?fromgroups#!topic/selenium-users/TPch0MFPLX4

<pre>    org.openqa.selenium.firefox.NotConnectedException: Unable to connect to host 127.0.0.1 on port 7055 after 45000 ms. Firefox console output:
    *** LOG addons.xpi: startup
    *** LOG addons.xpi: Ignoring file entry whose name is not a valid add-on ID: [...]</pre>

* ebean errors: Scala seems not to work well with ebean: https://groups.google.com/forum/?fromgroups#!topic/play-framework/WziP0HqjN4Y
* `Error with [models.ACLASS] It has not been enhanced but it's superClass [class play.db.ebean.Model] is? (You are not allowed to mix enhancement in a single inheritance hierarchy)` => mostly forgotten: build.scala: `val main = PlayProject(...etc...).settings(ebeanEnabled := true)`
* `Test CLass.method failed: java.sql.SQLException: Attempting to obtain a connection from a pool that has already been shutdown. ` => forgot `running(fakeApplication(inMemoryDatabase()), new Runnable() { [...]`
* IDEA does not find dependency, run in SBT reload, compile, idea
* don't include Google Guava Lib with SBT, it is already there with version 10.0 and extra Guavas JARs causes tests to crash
* Selenium tests: don't extend FluentTest, it does not work with play's `running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>()`
`render(play.api.data.Form<models.User>) in views.html.index cannot be applied to (play.data.Form<models.User>)`, maybe in Build.scala set mainLanguage from SCALA to JAVA