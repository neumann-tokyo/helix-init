(ns helix-init.components.random-cat
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [cljs.core.async :refer [go <!]]
            [cljs-http.client :as http]
            ["jotai" :as jotai]))

#_(defn loadable [http-request]
    (let [state-atom (jotai/atom {:state :before-load
                                  :data nil
                                  :error nil})
          drived-atom (jotai/atom
                       (fn [get] (get state-atom))
                       (fn [get set _update]
                         (go
                           (if (not= (:state (get state-atom)) :before-load)
                             (get state-atom)
                             (do
                               (set state-atom
                                    {:state :loading
                                     :data nil
                                     :error nil})
                               (try
                                 (let [response (<! http-request)]
                                   (set state-atom
                                        {:state :has-value
                                         :data response
                                         :error nil}))
                                 (catch js/Error e
                                   (set state-atom
                                        {:state :has-error
                                         :data nil
                                         :error e}))))))))]
      (jotai/atom (fn [get] (get drived-atom)))))

;; TODO cljs-http 用の loadable を自作する
;; (defonce random-cat-api-atom
;;   (jotai/atom
  ;;  (go (fn [_get] (<! (http/get "https://cataas.com/cat"
  ;;                               {:with-credentials? false
  ;;                                :query-params {"json" true}}))))))

(defnc random-cat-api [http-request]
  (let [[state, set-state] (hooks/use-state {:state :before-load
                                             :data nil
                                             :error nil})]
    (hooks/use-effect
     :once
     (do
       (set-state {:state :loading
                   :data nil
                   :error nil})
       (go
         (try
           (let [response (<! http-request)]
             (set-state
              {:state :has-value
               :data "ok"
               :error nil}))
           (catch js/Error e
             (js/console.error e)
             (set-state
              {:state :has-error
               :data nil
               :error e}))))))
    state))

(defnc random-cat []
  (print (random-cat-api (http/get "https://cataas.com/cat?json=true" {:with-credentials? false})))
  (d/div "..."))
