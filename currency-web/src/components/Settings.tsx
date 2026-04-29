import React, { useState } from 'react';
import { X, Save, RefreshCw } from 'lucide-react';

interface SettingsProps {
  isOpen: boolean;
  onClose: () => void;
  currentEndpoint: string;
  onSave: (endpoint: string) => void;
}

const Settings: React.FC<SettingsProps> = ({ isOpen, onClose, currentEndpoint, onSave }) => {
  const [endpoint, setEndpoint] = useState(currentEndpoint);

  if (!isOpen) return null;

  const handleSave = (e: React.FormEvent) => {
    e.preventDefault();
    onSave(endpoint);
    onClose();
  };

  return (
    <div className="fixed inset-0 z-[100] flex items-center justify-center p-4 bg-black/40 backdrop-blur-sm">
      <div className="glass-card w-full max-w-md relative animate-in fade-in zoom-in duration-300">
        <button
          onClick={onClose}
          className="absolute top-4 right-4 p-2 hover:bg-white/10 rounded-full transition-colors"
        >
          <X className="w-5 h-5 text-slate-400" />
        </button>

        <h2 className="text-xl font-bold mb-6 flex items-center gap-2">
          <RefreshCw className="w-5 h-5 text-primary-400" />
          API-Konfiguration
        </h2>

        <form onSubmit={handleSave} className="space-y-6">
          <div className="space-y-2">
            <label className="text-sm font-medium text-slate-300 ml-1">
              Daten-Endpunkt URL
            </label>
            <input
              type="url"
              value={endpoint}
              onChange={(e) => setEndpoint(e.target.value)}
              className="glass-input w-full font-mono text-sm"
              placeholder="https://api.exchangerate-api.com/v4/latest/USD"
              required
            />
            <p className="text-[10px] text-slate-500 ml-1">
              Muss ein JSON-Objekt mit einer <code className="text-primary-400">rates</code> Eigenschaft zurückgeben.
            </p>
          </div>

          <div className="flex gap-3 pt-2">
            <button
              type="button"
              onClick={() => setEndpoint('https://api.exchangerate-api.com/v4/latest/USD')}
              className="flex-1 px-4 py-2 rounded-xl bg-white/5 hover:bg-white/10 border border-white/10 transition-all text-sm"
            >
              Standard wiederherstellen
            </button>
            <button
              type="submit"
              className="flex-1 btn-primary flex items-center justify-center gap-2 text-sm"
            >
              <Save className="w-4 h-4" />
              Speichern
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Settings;
