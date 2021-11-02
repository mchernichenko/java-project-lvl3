setup:
	./gradlew wrapper --gradle-version 7.2

clean:
	./gradlew clean

install:
	./gradlew clean install

test:
	./gradlew test

run:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain

build:
	./gradlew build
