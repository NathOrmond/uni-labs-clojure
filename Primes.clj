;Returns a list of divisors
(defn get-divisors [n]
	(range 2 (Math/sqrt (+ 1 n)))
)


;TESTS
;(println (get-divisors 4))
;(println (get-divisors 101))

;--------------------------------
;evaluates n%x 
;returns true if no remainder
(defn divides? [x , n]
	(and (= 0 (mod n x)))
)

;TESTS
;(println (divides? 2 10))
;(println (divides? 4 10))

;--------------------------------
;Checks that for a list of divisors derived from n
;n does not divide wholly into any element of list
(defn no-divisors? [n]
	(def res 1)						;TODO Less crappy way of setting TRUE/FALSE?
	(doseq [x (get-divisors n)]
		(if(divides? x n)(def res 0))
	)
	(and (= 1 res))
)

;TESTS
;(println (no-divisors? 9))
;(println (no-divisors? 7))

;--------------------------------

(defn is-prime? [n]
	(def res 0)				; default false
	(if(< n 2)
		(def res 0) 		; <1 is not a prime number
		(if(no-divisors? n)	; If no other divisors
			(def res 1)		; Set res to return True (is a prime)
		)
	)
	(and (= 1 res))			
)

;TESTS
;(println (is-prime? 1))
;(println (is-prime? 2))
;(println (is-prime? 3))
;(println (is-prime? 4))
;(println (is-prime? 101))
;--------------------------------

(defn prime-seq [from, to]
	(def return-list [])
	(loop[i from]
		(when(< i (+ 1 to))
			(if(is-prime? i)
				(def return-list (conj return-list i))
			)
			(recur (+ i 1))
		)
	)

	(conj return-list)
)

;TESTS
;(println (prime-seq 50 100))
;(println (prime-seq 7 11))
;--------------------------------

(defn print-top-primes [from, to]
	(def primes (prime-seq from to)) 
	(def top-ten (reverse (take-last 10 primes)))
	(doseq [x top-ten]
		(println x)
	)
	(println "Total = " (reduce + top-ten))
)

(print-top-primes 50 100)
(print-top-primes 7 11)