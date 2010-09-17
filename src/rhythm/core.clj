(ns rhythm.core)

(defn welcome []
  (println "To talk to me, type (t XYZ), where XYZ is your answer to the question.")
  (println "A list begins with an open-parenthesis and ends with a close-parenthesis.")
  (println "1. What is an empty list?"))

(defn start []
  (welcome))
  
(def answer (ref ()))
  
(defmacro s [form]
  (dosync (ref-set answer `~form))
  nil)
  
(defmacro t [form]
  (= @answer `~form))
  
