# SBT
* use `~test` in the play console to run the tests on every source code change

# IntelliJ IDEA

* use the sbt console with the command `run` to start the play server

# Typical mistakes and errors

* wrong imports: the package play.api ist for Scala developers, play for Java developers
* Firefox WebDriver Plugin is outdated and does not work on the current Firefox: https://groups.google.com/forum/?fromgroups#!topic/selenium-users/TPch0MFPLX4

<pre>    org.openqa.selenium.firefox.NotConnectedException: Unable to connect to host 127.0.0.1 on port 7055 after 45000 ms. Firefox console output:
    *** LOG addons.xpi: startup
    *** LOG addons.xpi: Ignoring file entry whose name is not a valid add-on ID: [...]</pre>