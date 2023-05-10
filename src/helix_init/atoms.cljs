(ns helix-init.atoms
  (:require ["jotai" :as jotai]
            ["js-cookie" :as Cookies]))

(defonce sign-in-token-atom (jotai/atom (.get Cookies "sign-in-token")))
