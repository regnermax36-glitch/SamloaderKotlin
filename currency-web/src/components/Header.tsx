import React from 'react';
import { Coins, Settings as SettingsIcon } from 'lucide-react';

interface HeaderProps {
  onSettingsClick: () => void;
}

const Header: React.FC<HeaderProps> = ({ onSettingsClick }) => {
  return (
    <header className="sticky top-0 z-50 w-full px-6 py-4 glass mb-8">
      <div className="max-w-7xl mx-auto flex justify-between items-center">
        <div className="flex items-center gap-3">
          <div className="p-2 bg-primary-500 rounded-xl shadow-lg shadow-primary-500/30">
            <Coins className="w-6 h-6 text-white" />
          </div>
          <h1 className="text-2xl font-bold tracking-tight bg-clip-text text-transparent bg-gradient-to-r from-white to-primary-200">
            Bifrost FX
          </h1>
        </div>

        <button
          onClick={onSettingsClick}
          className="p-2 hover:bg-white/10 rounded-full transition-colors group"
          title="Einstellungen"
        >
          <SettingsIcon className="w-6 h-6 text-slate-300 group-hover:rotate-90 transition-transform duration-300" />
        </button>
      </div>
    </header>
  );
};

export default Header;
