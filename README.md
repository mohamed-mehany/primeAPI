# primeAPI

## Usage

### CLI
Download jar at ``` bin/primeAPI-cli.jar ```
Then run the following command:
``` 
java -jar primeAPI-cli.jar -s 1 -e 100 -t erato 
```
#### Algorithm types:
- **erato**: Eratosthenes Sieve, please check:
- **parallel**: Multi threaded segmented version of Eratosthenes Sieve, (CPU efficient) please check:
- **miller**: Memory efficient implementation of Miller Rabin primality test algorithm, please check:
- **parallelMiller**: Multi threaded version of Miller Rabin primality test algorithm
 
### Server
To run the server, we'll use docker for simplicity.
```
docker run -p 9000:9000 
