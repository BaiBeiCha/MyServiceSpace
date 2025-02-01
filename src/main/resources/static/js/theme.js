// Theme management
const STORAGE_KEY = 'theme-preference';
const DARK_THEME = 'dark-theme';
const LIGHT_THEME = 'light-theme';

class ThemeManager {
    constructor() {
        this.themeToggle = document.querySelector('.theme-toggle');
        this.initialize();
        this.removeInitialTransition();
    }

    removeInitialTransition() {
        const style = document.createElement('style');
        style.textContent = `
            body {
                transition: none !important;
            }
        `;
        document.head.appendChild(style);

        requestAnimationFrame(() => {
            document.head.removeChild(style);
        });
    }

    initialize() {
        this.setTheme(this.getStoredTheme());

        this.themeToggle?.addEventListener('click', () => this.toggleTheme());
        this.themeToggle?.addEventListener('keydown', (e) => {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                this.toggleTheme();
            }
        });

        window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change',
            (e) => {
            if (!this.getStoredTheme()) {
                this.setTheme(e.matches ? DARK_THEME : LIGHT_THEME);
            }
        });
    }

    getStoredTheme() {
        try {
            return localStorage.getItem(STORAGE_KEY) || 
                   (window.matchMedia('(prefers-color-scheme: dark)').matches ? DARK_THEME : LIGHT_THEME);
        } catch (error) {
            console.warn('LocalStorage is not available:', error);
            return LIGHT_THEME;
        }
    }

    setTheme(theme) {
        document.body.className = theme;
        this.updateToggleButton(theme);
        
        try {
            localStorage.setItem(STORAGE_KEY, theme);
        } catch (error) {
            console.warn('Failed to save theme preference:', error);
        }
    }

    toggleTheme() {
        const newTheme = document.body.className === DARK_THEME ? LIGHT_THEME : DARK_THEME;
        this.setTheme(newTheme);
    }

    updateToggleButton(theme) {
        if (this.themeToggle) {
            const icon = theme === DARK_THEME ? '☀️' : '🌙';
            const label = theme === DARK_THEME ? 'Switch to light theme' : 'Switch to dark theme';
            
            this.themeToggle.innerHTML = `
                ${icon}
                <span class="sr-only">${label}</span>
            `;
            this.themeToggle.setAttribute('aria-label', label);
        }
    }
}

new ThemeManager();