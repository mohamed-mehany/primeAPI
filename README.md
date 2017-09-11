# primeAPI

## Usage

### CLI
Download jar at ``` bin/primeAPI-cli.jar ```
Then run the following command:
``` 
java -jar primeAPI-cli.jar -s 1 -e 100 -t erato 
```
#### Algorithm types:
- **erato**: Eratosthenes Sieve, please check: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
- **parallel**: Multi threaded segmented version of Eratosthenes Sieve, (CPU efficient) please check: http://sweet.ua.pt/tos/software/prime_sieve.html
- **miller**: Memory efficient implementation of Miller Rabin primality test algorithm, please check: https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test
- **parallelMiller**: Multi threaded version of Miller Rabin primality test algorithm
 
### Server
To run the server, we'll use docker for simplicity.
```
docker run mohamedmehany/primeapi:1.0-SNAPSHOT -p 9000:9000 
```

To call the prime generation end point, you can use curl or any REST API client:

```
curl 'http://localhost:9000/sieve?start=1&end=100&type=erato'
``` 

To call the stored queries listing endpoint:
```
curl 'http://localhost:9000/queries'
``` 


