(ns helix-init.atoms
  (:require ["jotai" :as jotai]))

(defonce sign-in-token-atom (jotai/atom nil))
