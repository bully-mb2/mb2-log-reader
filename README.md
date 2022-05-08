# MB2 Log Reader
<img align="right" width="115" height="115" src="https://user-images.githubusercontent.com/86576295/167314810-c9b6a021-6a41-463f-b35f-6ac3b825be7a.png">
MB2 Log Reader is an effort by the [Movie Battles community](https://community.moviebattles.org/) to create a framework on which server plugins may be built. This is achieved by reading, parsing and formatting console log data into an easily accessible format.

## For server owners
To run MB2 Log Reader we first need to set up some infrastructure. We need:
1. Install a [JRE](https://java.com/en/download/manual.jsp) that can run Java 11 or higher
2. Install [bully-mb2's fork of OpenJK](https://github.com/bully-mb2/OpenJK/releases)
3. Run an [MQTT broker](https://mosquitto.org/download/) as a service
4. Configure MB2 server.cfg
    ```
    seta logremote "127.0.0.1:63336" // Default "" if set to an ip:port it will write log data to remote server, example = "1.1.1.1:50000"
    set g_logExplicit "3"
    set g_logClientInfo "1"
    set com_logChat "2"
    ```
5. Run MB2 Log Reader and configure the generated application.properties
    ```
    java -jar mb2-log-reader-VERSION.jar
    ```

## For plugin developers
To hook into the data generated by MB2 Log Reader you need an MQTT client and a way to generate classes from the schema defined [here](https://github.com/bully-mb2/mb2-log-reader/tree/master/src/main/resources/schema).

Example usage: [MB2 Plugin Template](https://github.com/bully-mb2/mb2-plugin-template)

## License
MB2 Log Reader is licensed under GPLv2 as free software. You are free to use, modify and redistribute MB2 Log Reader following the terms in LICENSE.txt
