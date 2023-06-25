(ns helix-init.components.random-cat
  (:require [helix.core :refer [defnc $]]
            ["@chakra-ui/react" :refer [AspectRatio Image Spinner]]
            ["axios$default" :as axios]
            ["jotai" :as jotai]
            ["jotai/utils" :as jotai-utils]))

(defonce random-cat-api-atom
  (jotai/atom (axios/get
               "https://cataas.com/cat"
               #js {:withCredentials false
                    :params #js {:json true}})))
(defonce random-cat-api-loadable-atom
  (jotai-utils/loadable random-cat-api-atom))

(defnc random-cat []
  (let [[random-cat-api] (jotai/useAtom random-cat-api-loadable-atom)
        image-url (some-> (.-data random-cat-api)
                          (js->clj :keywordize-keys true)
                          (get-in [:data :url])
                          ((fn [path]
                             (str "https://cataas.com" path))))]
    (if image-url
      ($ AspectRatio {:maxW "400px" :ratio (/ 4 3)}
         ($ Image {:src image-url :alt "cat" :objectFit "cover"}))
      ($ Spinner))))
