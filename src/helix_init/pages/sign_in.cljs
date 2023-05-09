(ns helix-init.pages.sign-in
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            ["react-hook-form" :refer [useForm]]))

;; https://github.com/dvingo/malli-react-hook-form
(defnc sign-in-page []
  (let [form (useForm)
        register (.-register form)
        handleSubmit (.-handleSubmit form)
        errors (-> form .-formState .-errors)
        register-email (register "email" #js {:required true})
        register-password (register "password" #js {:required true})
        [api-errors set-api-errors] (hooks/use-state nil)
        on-submit (fn [data]
                    (if (and (= (.-email data) "user1@example.com")
                             (= (.-password data) "password"))
                      (js/console.log data)
                      (set-api-errors ["Invalid email or password."])))]
    (d/div
     (d/h1 {:class-name "text-4xl"} "Sign In")
     (d/form
      {:class-name "flex flex-col gap-4 w-96 mt-4"
       :on-submit (handleSubmit on-submit)}
      (d/div
       {:class-name "flex flex-col gap-2"}
       (d/label {:for "email"} "Email")
       (d/input
        {:type "email"
         :id "email"
         :class-name "border border-gray-300 rounded-md p-2"
         :placeholder "Email"
         :name (.-name register-email)
         :on-blur (.-onBlur register-email)
         :on-change (.-onChange register-email)
         :ref (.-ref register-email)})
       (when (.-email errors)
         (d/span {:class-name "text-red-500"} "This field is required")))
      (d/div
       {:class-name "flex flex-col gap-2"}
       (d/label {:for "password"} "Password")
       (d/input
        {:type "password"
         :id "password"
         :class-name "border border-gray-300 rounded-md p-2"
         :placeholder "Password"
         :name (.-name register-password)
         :on-blur (.-onBlur register-password)
         :on-change (.-onChange register-password)
         :ref (.-ref register-password)})
       (when (.-password errors)
         (d/span {:class-name "text-red-500"} "This field is required")))
      (map-indexed (fn [index error]
                     (d/div {:class-name "text-red-500" :key index} error)) api-errors)
      (d/button
       {:type "submit"
        :class-name "bg-blue-500 text-white rounded-md p-2"}
       "Sign In")))))
