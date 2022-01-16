Feli Utils
====

This Minecraft Plugin is mainly used by my own Plugins to supply some Utility Methods wich I often use for my plugins. Of course if you want you are allowed to use this plugin in your own Projects 😄

</br>

Releases
====

Last Stable Release: [Click here](https://jenkins.ehemanns.de/job/FeliUtils/lastRelease/)

Last Development Version: [Click here](https://jenkins.ehemanns.de/job/FeliUtils/lastStableBuild)

All versions are build on our Jenkins Server: [here](https://jenkins.ehemanns.de/job/FeliUtils/)

</br>

Use FeliUtils in your own Plugin
====

For maven Users you can just add our repository.

        <repository>
            <id>ehemanns-repo</id>
            <url>https://repo.ehemanns.de/repository/maven-public/</url>
        </repository>

Then you can just add the plugin as your dependency:

        <dependency>
            <groupId>de.feli490.plugins</groupId>
            <artifactId>FeliUtils</artifactId>
            <version>CHOOSEN-VERSION</version>
            <scope>provided</scope>
        </dependency>

Now you can just use every Util Method / Class of the Plugin.</br>
To ensure complete functionality you should add this Plugin as (Soft-)Dependency in your <code>Plugin.yml</code>:

    depend: [PlayerUUIDCache]