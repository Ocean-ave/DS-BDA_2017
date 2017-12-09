#!/bin/bash

docker-compose up -d
(cd kafka-producer; sbt "run 73H0Ozj8EBiBOjXHIL5kG8Q QmXwcd0GvthTl84OThVVxA2SJTmYIPxK65Sq6qztJXONvwQCvD 355442353-CHEkGLwTlXvJm0McWQsKx78VzPX96K17EjrhMCi2 HGRfmQAjVpupFBsvZTbUwwaOy8bxHfbFxcYKfIaVhhzFg") &
(cd kafka-hdfs; sbt run) &