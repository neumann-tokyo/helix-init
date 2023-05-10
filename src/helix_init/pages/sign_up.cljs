(ns helix-init.pages.sign-up
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            ["react-router-dom" :refer [useNavigate]]))

(defnc sign-up-page []
  (let [navigate (useNavigate)
        [api-errors set-api-errors] (hooks/use-state nil)
        on-submit (fn [e]
                    (.preventDefault e)
                    (let [_email (-> e .-target .-email .-value)
                          password (-> e .-target .-password .-value)
                          password-confirmation (-> e .-target .-password .-value)]
                      (if (not= password password-confirmation)
                        (set-api-errors ["Password confirmation does not match."])
                        (navigate "/"))))]
    (d/div
     (d/h1 {:class-name "text-4xl"} "Sign Up")
     (d/form
      {:class-name "flex flex-col gap-4 w-96 mt-4"
       :on-submit on-submit}
      (d/div
       {:class-name "flex flex-col gap-2"}
       (d/label {:for "email"} "Email")
       (d/input
        {:type "email"
         :id "email"
         :name "email"
         :class-name "border border-gray-300 rounded-md p-2"
         :placeholder "Email"
         :required true}))
      (d/div
       {:class-name "flex flex-col gap-2"}
       (d/label {:for "password"} "Password")
       (d/input
        {:type "password"
         :id "password"
         :name "password"
         :class-name "border border-gray-300 rounded-md p-2"
         :placeholder "Password"
         :required true}))
      (d/div
       {:class-name "flex flex-col gap-2"}
       (d/label {:for "password-confirmation"} "Password Confirmation")
       (d/input
        {:type "password"
         :id "password-confirmation"
         :name "password-confirmation"
         :class-name "border border-gray-300 rounded-md p-2"
         :placeholder "Password Confirmation"
         :required true}))
      (map-indexed (fn [index error]
                     (d/div {:class-name "text-red-500" :key index} error)) api-errors)
      (d/button
       {:type "submit"
        :class-name "bg-blue-500 text-white rounded-md p-2"}
       "Sign Up")))))
