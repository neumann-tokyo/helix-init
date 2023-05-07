(ns helix-init.pages.sign-in
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            ["react-hook-form" :refer [useForm]]))

(defn on-submit [data]
  (js/console.log data))

;; const { register, handleSubmit, watch, formState: { errors } } = useForm();
(defnc sign-in-page []
  (let [form (useForm)
        register (.-register form)
        handleSubmit (.-handleSubmit form)
        watch (.-watch form)
        errors (-> form .-formState .-errors)]
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
         :name "email"
         :id "email"
         :class-name "border border-gray-300 rounded-md p-2"
         :placeholder "Email"}))
      (d/div
       {:class-name "flex flex-col gap-2"}
       (d/label {:for "password"} "Password")
       (d/input
        {:type "password"
         :name "password"
         :id "password"
         :class-name "border border-gray-300 rounded-md p-2"
         :placeholder "Password"}))
      (d/button
       {:type "submit"
        :class-name "bg-blue-500 text-white rounded-md p-2"}
       "Sign In")))))
