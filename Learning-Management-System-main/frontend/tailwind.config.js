/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          DEFAULT: '#0ea5e9', // Sky blue 500
          dark: '#0284c7', // Sky blue 600
        },
        secondary: {
          DEFAULT: '#38bdf8', // Sky blue 400
          dark: '#0ea5e9', // Sky blue 500
        },
        accent: {
          DEFAULT: '#0ea5e9', // Sky blue 500
          dark: '#0284c7', // Sky blue 600
        },
        warning: {
          DEFAULT: '#facc15',
          dark: '#cc9900ff',
        },
      },
      keyframes: {
        fadeInUp: {
          '0%': { opacity: 0, transform: 'translateY(20px)' },
          '100%': { opacity: 1, transform: 'translateY(0)' },
        },
      },
      animation: {
        fadeInUp: 'fadeInUp 0.8s ease-out forwards',
      },
      backgroundImage: {
        'gradient-primary': 'linear-gradient(to right, #0ea5e9, #38bdf8)',
      },
    },
  },
  plugins: [],
}
