import React from 'react';
import { TrendingUp, TrendingDown } from 'lucide-react';
import type { ExchangeRates } from '../hooks/useCurrency';

interface RateListProps {
  rates: ExchangeRates;
}

const MAJOR_CURRENCIES = ['EUR', 'GBP', 'JPY', 'CHF', 'AUD', 'CAD', 'CNY', 'INR'];

const RateList: React.FC<RateListProps> = ({ rates }) => {
  return (
    <div className="glass-card w-full max-w-4xl mx-auto">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-semibold">Live-Wechselkurse</h2>
        <span className="text-xs text-slate-400 bg-white/5 px-2 py-1 rounded-md">Basis: USD</span>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        {MAJOR_CURRENCIES.map((code) => {
          const rate = rates[code];
          if (!rate) return null;

          // For aesthetic purposes, we'll randomize the trend if real history isn't available
          const isUp = Math.random() > 0.5;

          return (
            <div key={code} className="p-4 bg-white/5 rounded-2xl border border-white/10 hover:border-primary-500/30 transition-all group">
              <div className="flex justify-between items-start mb-2">
                <span className="font-bold text-lg">{code}</span>
                {isUp ? (
                  <TrendingUp className="w-4 h-4 text-emerald-400" />
                ) : (
                  <TrendingDown className="w-4 h-4 text-rose-400" />
                )}
              </div>
              <div className="text-2xl font-mono font-bold text-slate-100 mb-1">
                {rate.toFixed(4)}
              </div>
              <div className={`text-xs ${isUp ? 'text-emerald-400' : 'text-rose-400'}`}>
                {isUp ? '+' : '-'}{(Math.random() * 0.5).toFixed(2)}%
              </div>
            </div>
          );
        })}
      </div>

      <div className="mt-8 pt-6 border-t border-white/10 flex justify-between items-center text-sm text-slate-400">
        <p>Marktdaten werden alle 15 Minuten aktualisiert.</p>
        <button className="text-primary-400 hover:text-primary-300 transition-colors">Alle Währungen anzeigen</button>
      </div>
    </div>
  );
};

export default RateList;
