/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './resources/public/**/*.{html,js}',
  ],
  theme: {
    extend: {},
  },
  plugins: [require('@tailwindcss/forms')],
}

