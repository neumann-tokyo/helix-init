(ns helix-init.components.require-auth
  (:require [helix.core :refer [defnc $]]
            ["react-router-dom" :refer [Navigate]]
            ["jotai" :refer [useAtom]]
            [helix-init.atoms :refer [sign-in-token-atom]]))

(defnc require-auth [{:keys [children]}]
  (let [[sign-in-token _] (useAtom sign-in-token-atom)]
    (if sign-in-token
      ($ children)
      ($ Navigate {:to "/"}))))
