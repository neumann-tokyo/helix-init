(ns helix-init.app
  (:require [helix.core :refer [defnc $]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react-dom/client" :as rdom]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.coercion.spec :as rss]))

;; define components using the `defnc` macro
(defnc greeting
  "A component which greets a user."
  [{:keys [name]}]
  ;; use helix.dom to create DOM elements
  (d/div "Hello, " (d/strong name) "!"))

(defnc home-page []
  (let [[state set-state] (hooks/use-state {:name "Helix User"})]
    (d/div
     (d/h1 {:class-name "text-4xl"} "Welcome!")
      ;; create elements out of components
     ($ greeting {:name (:name state)})
     (d/input {:value (:name state)
               :on-change #(set-state assoc :name (.. % -target -value))}))))

(defnc about-page []
  (d/div
   (d/h1 {:class-name "text-4xl"} "About")
   (d/p "This is a Helix app.")))

(defonce root (rdom/createRoot (js/document.getElementById "app")))

(defonce match (atom nil))

(defnc current-page []
  (d/div
   (d/h1 {:class-name "text-4xl"} "Header")
   (when @match
     (let [view (get-in @match [:data :view])]
       ($ view @match)))))

(def routes
  [["/" {:name "home"
         :view home-page}]
   ["/about/" {:name "about"
               :view about-page}]])

(defn ^:export init []
  (rfe/start!
   (rf/router routes)
   (fn [m] (reset! match m))
   {:use-fragment false})
  (.render root ($ current-page)))
