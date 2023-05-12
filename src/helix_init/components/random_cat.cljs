(ns helix-init.components.random-cat
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [cljs.core.async :refer [go <!]]
            [cljs-http.client :as http]
            ["jotai" :as jotai]
            ["jotai/utils" :as jotai-utils]))

;; TODO cljs-http 用の loadable を自作する
(defonce random-cat-api-atom
  (jotai/atom
   (go (fn [_get] (<! (http/get "https://cataas.com/cat"
                                {:with-credentials? false
                                 :query-params {"json" true}}))))))
(defonce random-cat-api-loadable-atom
  (jotai-utils/loadable random-cat-api-atom))

(defnc random-cat []
  (let [[random-cat-api] (jotai/useAtom random-cat-api-loadable-atom)]
    (print (.-data random-cat-api))
    (d/div
     #_(case (.-state random-cat-api)
         "loading" ($ d/div "Loading...")
         "hasError" ($ d/div (.-error random-cat-api))
         (d/div (.-data random-cat-api))))))
