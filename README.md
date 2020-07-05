# maven-artifact-test
Checks which Maven artifact scopes are included in the runtime scope.
The result is printed to the console as part of a test case.

Usage:
mvn verify

Result:

|Scope|Artifact Scope|Included|
|--------------------|--------------------|----------|
|runtime|compile|YES|
|runtime|compile+runtime|YES|
|runtime|import|YES|
|runtime|provided|NO|
|runtime|runtime|YES|
|runtime|runtime+system|YES|
|runtime|system|NO|
|runtime|test|NO|
