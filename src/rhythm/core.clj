(ns rhythm.core)

(defn welcome []
  (println "To talk to me, type (answer XYZ), where XYZ is your answer to the question.")
  (println "You can use this for short: (a XYZ)")
  (println "My questions will appear in quotes.")
  (println "A list begins with an open-parenthesis and ends with a close-parenthesis."))

(def answer (ref ()))
(def number (ref 0))

(defmacro s [form]
  (dosync (ref-set answer `~form))
  nil)

(defmacro answer [form]
  (if (= @answer `~form)
    (println "word up")))
    ;(question @number)
    ;(question (+ 1 @number)))
(def a answer)

(defmacro check [attempt]
  (= @answer `~attempt))

(defn question-1 []
  (s ())
  "1. What is an empty list?")
  
(defn question [n]
  (dosync (ref-set number n))
  (println "\n")
  (condp = n
    1 question-1))

(defn start []
  (welcome)
  ((question 1)))

