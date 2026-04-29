import React, { useState, useMemo } from 'react';
import { ShoppingCart, ArrowRight, Wallet, AlertCircle, CheckCircle2 } from 'lucide-react';
import type { ExchangeRates } from '../hooks/useCurrency';

interface TradingTerminalProps {
  rates: ExchangeRates;
  holdings: { [key: string]: number };
  onTrade: (type: 'BUY' | 'SELL', from: string, to: string, amount: number, rate: number) => void;
}

const TradingTerminal: React.FC<TradingTerminalProps> = ({ rates, holdings, onTrade }) => {
  const [tradeType, setTradeType] = useState<'BUY' | 'SELL'>('BUY');
  const [selectedCurrency, setSelectedCurrency] = useState('EUR');
  const [amount, setAmount] = useState<number>(0);
  const [status, setStatus] = useState<{ type: 'success' | 'error', message: string } | null>(null);

  const currencies = useMemo(() => Object.keys(rates).filter(c => c !== 'USD').sort(), [rates]);

  const currentRate = rates[selectedCurrency] || 0;
  const usdBalance = holdings['USD'] || 0;
  const assetBalance = holdings[selectedCurrency] || 0;

  const handleTrade = (e: React.FormEvent) => {
    e.preventDefault();
    setStatus(null);

    try {
      if (amount <= 0) throw new Error('Der Betrag muss größer als 0 sein');

      if (tradeType === 'BUY') {
        // Buy EUR with USD: from USD to EUR. Rate is USD -> EUR (rates[EUR])
        onTrade('BUY', 'USD', selectedCurrency, amount, currentRate);
      } else {
        // Sell EUR for USD: from EUR to USD. Rate is EUR -> USD (1 / rates[EUR])
        onTrade('SELL', selectedCurrency, 'USD', amount, 1 / currentRate);
      }

      setStatus({
        type: 'success',
        message: `Erfolgreich ${amount} ${tradeType === 'BUY' ? 'USD im Wert von' : ''} ${selectedCurrency} ${tradeType === 'BUY' ? 'gekauft' : 'verkauft'}`
      });
      setAmount(0);
    } catch (err) {
      setStatus({ type: 'error', message: err instanceof Error ? err.message : 'Handel fehlgeschlagen' });
    }
  };

  return (
    <div className="glass-card w-full max-w-2xl mx-auto">
      <div className="flex gap-1 bg-white/5 p-1 rounded-2xl mb-6">
        <button
          onClick={() => setTradeType('BUY')}
          className={`flex-1 py-2 rounded-xl font-semibold transition-all ${tradeType === 'BUY' ? 'bg-emerald-500 text-white shadow-lg shadow-emerald-500/20' : 'text-slate-400 hover:text-white'}`}
        >
          Kaufen
        </button>
        <button
          onClick={() => setTradeType('SELL')}
          className={`flex-1 py-2 rounded-xl font-semibold transition-all ${tradeType === 'SELL' ? 'bg-rose-500 text-white shadow-lg shadow-rose-500/20' : 'text-slate-400 hover:text-white'}`}
        >
          Verkaufen
        </button>
      </div>

      <form onSubmit={handleTrade} className="space-y-6">
        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2">
            <label className="text-xs text-slate-400 ml-1 uppercase tracking-wider font-bold">Währung</label>
            <select
              value={selectedCurrency}
              onChange={(e) => setSelectedCurrency(e.target.value)}
              className="glass-input w-full appearance-none cursor-pointer"
            >
              {currencies.map(c => <option key={c} value={c} className="bg-slate-800">{c}</option>)}
            </select>
          </div>
          <div className="space-y-2">
            <label className="text-xs text-slate-400 ml-1 uppercase tracking-wider font-bold">
              {tradeType === 'BUY' ? 'Betrag (USD)' : `Betrag (${selectedCurrency})`}
            </label>
            <input
              type="number"
              value={amount || ''}
              onChange={(e) => setAmount(Number(e.target.value))}
              className="glass-input w-full"
              placeholder="0.00"
            />
          </div>
        </div>

        <div className="p-4 bg-white/5 rounded-2xl border border-white/10 space-y-3">
          <div className="flex justify-between text-sm">
            <span className="text-slate-400">Aktueller Kurs</span>
            <span className="font-mono text-primary-400">1 USD = {currentRate.toFixed(4)} {selectedCurrency}</span>
          </div>
          <div className="flex justify-between text-sm">
            <span className="text-slate-400">Verfügbares Guthaben</span>
            <span className="font-mono text-white">
              {tradeType === 'BUY'
                ? `${usdBalance.toLocaleString(undefined, { minimumFractionDigits: 2 })} USD`
                : `${assetBalance.toLocaleString(undefined, { minimumFractionDigits: 4 })} ${selectedCurrency}`
              }
            </span>
          </div>
          <div className="pt-2 border-t border-white/5 flex justify-between items-center">
            <span className="text-slate-300 font-semibold">Sie erhalten</span>
            <div className="text-right">
              <div className="text-lg font-bold text-white">
                {tradeType === 'BUY'
                  ? (amount * currentRate).toLocaleString(undefined, { minimumFractionDigits: 2 })
                  : (amount / currentRate).toLocaleString(undefined, { minimumFractionDigits: 2 })
                }
                <span className="ml-1 text-sm text-slate-400">{tradeType === 'BUY' ? selectedCurrency : 'USD'}</span>
              </div>
            </div>
          </div>
        </div>

        {status && (
          <div className={`p-4 rounded-xl flex items-center gap-3 animate-in fade-in slide-in-from-top-2 ${status.type === 'success' ? 'bg-emerald-500/10 text-emerald-400 border border-emerald-500/20' : 'bg-rose-500/10 text-rose-400 border border-rose-500/20'}`}>
            {status.type === 'success' ? <CheckCircle2 className="w-5 h-5" /> : <AlertCircle className="w-5 h-5" />}
            <span className="text-sm font-medium">{status.message}</span>
          </div>
        )}

        <button
          type="submit"
          className={`w-full py-4 rounded-2xl font-bold text-lg shadow-xl transform active:scale-[0.98] transition-all flex items-center justify-center gap-2 ${tradeType === 'BUY' ? 'bg-gradient-to-r from-emerald-500 to-teal-500 shadow-emerald-500/20' : 'bg-gradient-to-r from-rose-500 to-orange-500 shadow-rose-500/20'}`}
        >
          {tradeType === 'BUY' ? <ShoppingCart className="w-5 h-5" /> : <ArrowRight className="w-5 h-5" />}
          {tradeType === 'BUY' ? 'Kaufauftrag ausführen' : 'Verkaufsauftrag ausführen'}
        </button>
      </form>

      <div className="mt-6 flex items-center gap-2 justify-center text-xs text-slate-500">
        <Wallet className="w-3 h-3" />
        <span>Sofortige Ausführung • Keine Gebühren • Nur virtuelles Guthaben</span>
      </div>
    </div>
  );
};

export default TradingTerminal;
