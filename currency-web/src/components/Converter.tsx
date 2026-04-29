import React, { useState, useMemo } from 'react';
import { ArrowRightLeft } from 'lucide-react';
import type { ExchangeRates } from '../hooks/useCurrency';

interface ConverterProps {
  rates: ExchangeRates;
}

const Converter: React.FC<ConverterProps> = ({ rates }) => {
  const [amount, setAmount] = useState<number>(1);
  const [fromCurrency, setFromCurrency] = useState<string>('USD');
  const [toCurrency, setToCurrency] = useState<string>('EUR');

  const currencies = useMemo(() => Object.keys(rates).sort(), [rates]);

  const convertedAmount = useMemo(() => {
    if (!rates[fromCurrency] || !rates[toCurrency]) return 0;
    return (amount / rates[fromCurrency]) * rates[toCurrency];
  }, [amount, fromCurrency, toCurrency, rates]);

  const swapCurrencies = () => {
    setFromCurrency(toCurrency);
    setToCurrency(fromCurrency);
  };

  return (
    <div className="glass-card w-full max-w-2xl mx-auto mb-12">
      <h2 className="text-xl font-semibold mb-6">Währungsrechner</h2>

      <div className="grid grid-cols-1 md:grid-cols-[1fr_auto_1fr] gap-4 items-end">
        <div className="space-y-2">
          <label className="text-sm text-slate-400 ml-1">Betrag</label>
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(Number(e.target.value))}
            className="glass-input w-full text-lg"
            placeholder="0.00"
          />
        </div>

        <div className="flex flex-col gap-4 md:flex-row md:items-end">
          <div className="space-y-2 flex-1">
            <label className="text-sm text-slate-400 ml-1">Von</label>
            <select
              value={fromCurrency}
              onChange={(e) => setFromCurrency(e.target.value)}
              className="glass-input w-full appearance-none cursor-pointer"
            >
              {currencies.map((curr) => (
                <option key={curr} value={curr} className="bg-slate-800 text-white">
                  {curr}
                </option>
              ))}
            </select>
          </div>

          <button
            onClick={swapCurrencies}
            className="p-3 bg-white/5 hover:bg-white/10 rounded-xl transition-all self-end md:mb-1"
          >
            <ArrowRightLeft className="w-5 h-5 text-primary-400" />
          </button>

          <div className="space-y-2 flex-1">
            <label className="text-sm text-slate-400 ml-1">Nach</label>
            <select
              value={toCurrency}
              onChange={(e) => setToCurrency(e.target.value)}
              className="glass-input w-full appearance-none cursor-pointer"
            >
              {currencies.map((curr) => (
                <option key={curr} value={curr} className="bg-slate-800 text-white">
                  {curr}
                </option>
              ))}
            </select>
          </div>
        </div>
      </div>

      <div className="mt-8 p-6 bg-primary-500/10 rounded-2xl border border-primary-500/20 text-center">
        <div className="text-sm text-slate-400 mb-1">
          {amount} {fromCurrency} =
        </div>
        <div className="text-3xl font-bold text-white">
          {convertedAmount.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 4 })} {toCurrency}
        </div>
        <div className="text-xs text-slate-500 mt-2 italic">
          1 {fromCurrency} = {(convertedAmount / (amount || 1)).toFixed(6)} {toCurrency}
        </div>
      </div>
    </div>
  );
};

export default Converter;
