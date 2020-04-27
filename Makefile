.DEFAULT_GOAL := build-run

build-run: build run

run:
	java -jar ./target/article-*.jar

clean:
	./mvnw clean -q

build:
	./mvnw package -amd -T 1C -DskipTests=true

test:
	./mvnw test -amd
