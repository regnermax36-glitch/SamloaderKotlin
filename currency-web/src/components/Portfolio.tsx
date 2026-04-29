import React from 'react';
import { PieChart, Wallet, TrendingUp, ArrowUpRight, ArrowDownRight } from 'lucide-react';
import type { PortfolioHoldings } from '../hooks/usePortfolio';
import type { ExchangeRates } from '../hooks/useCurrency';

interface PortfolioProps {
  holdings: PortfolioHoldings;
  rates: ExchangeRates;
}

const Portfolio: React.FC<PortfolioProps> = ({ holdings, rates }) => {
  const totalValueUSD = Object.entries(holdings).reduce((total, [curr, amount]) => {
    if (curr === 'USD') return total + amount;
    const rate = rates[curr];
    return total + (rate ? amount / rate : 0);
  }, 0);

  const profitLoss = totalValueUSD - 10000;
  const isProfit = profitLoss >= 0;

  return (
    <div className="space-y-6 max-w-4xl mx-auto">
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="glass-card flex flex-col justify-between">
          <div className="flex justify-between items-start">
            <span className="text-slate-400 text-sm font-medium">Gesamtguthaben (USD)</span>
            <Wallet className="w-5 h-5 text-primary-400" />
          </div>
          <div className="mt-4">
            <div className="text-3xl font-bold text-white">
              ${totalValueUSD.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
            </div>
            <div className={`flex items-center gap-1 mt-1 text-sm ${isProfit ? 'text-emerald-400' : 'text-rose-400'}`}>
              {isProfit ? <ArrowUpRight className="w-4 h-4" /> : <ArrowDownRight className="w-4 h-4" />}
              {Math.abs(profitLoss / 100).toFixed(2)}% (${Math.abs(profitLoss).toFixed(2)})
            </div>
          </div>
        </div>

        <div className="glass-card flex flex-col justify-between">
          <div className="flex justify-between items-start">
            <span className="text-slate-400 text-sm font-medium">Aktive Assets</span>
            <PieChart className="w-5 h-5 text-indigo-400" />
          </div>
          <div className="mt-4">
            <div className="text-3xl font-bold text-white">
              {Object.keys(holdings).filter(k => holdings[k] > 0.0001).length}
            </div>
            <div className="text-slate-400 text-sm mt-1">Diversifizierte Währungen</div>
          </div>
        </div>

        <div className="glass-card flex flex-col justify-between">
          <div className="flex justify-between items-start">
            <span className="text-slate-400 text-sm font-medium">Markt-Status</span>
            <TrendingUp className="w-5 h-5 text-emerald-400" />
          </div>
          <div className="mt-4">
            <div className="text-3xl font-bold text-white">Aktiv</div>
            <div className="text-slate-400 text-sm mt-1">Echtzeit-Synchronisierung</div>
          </div>
        </div>
      </div>

      <div className="glass-card">
        <h3 className="text-lg font-bold mb-6">Asset-Aufteilung</h3>
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="text-left text-xs text-slate-400 uppercase tracking-widest border-b border-white/5">
                <th className="pb-4 font-bold">Asset</th>
                <th className="pb-4 font-bold text-right">Bestand</th>
                <th className="pb-4 font-bold text-right">Wert (USD)</th>
                <th className="pb-4 font-bold text-right">Gewichtung</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-white/5">
              {Object.entries(holdings)
                .filter(([_, amount]) => amount > 0.000001)
                .sort((a, b) => {
                  const valA = a[0] === 'USD' ? a[1] : a[1] / (rates[a[0]] || 1);
                  const valB = b[0] === 'USD' ? b[1] : b[1] / (rates[b[0]] || 1);
                  return valB - valA;
                })
                .map(([curr, amount]) => {
                  const valUSD = curr === 'USD' ? amount : amount / (rates[curr] || 1);
                  const allocation = (valUSD / totalValueUSD) * 100;

                  return (
                    <tr key={curr} className="group hover:bg-white/5 transition-colors">
                      <td className="py-4 font-bold text-slate-200">{curr}</td>
                      <td className="py-4 text-right font-mono text-white">
                        {amount.toLocaleString(undefined, { minimumFractionDigits: curr === 'USD' ? 2 : 4 })}
                      </td>
                      <td className="py-4 text-right font-mono text-slate-300">
                        ${valUSD.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
                      </td>
                      <td className="py-4 text-right">
                        <div className="flex items-center justify-end gap-3">
                          <div className="w-24 h-1.5 bg-white/5 rounded-full overflow-hidden hidden sm:block">
                            <div
                              className="h-full bg-primary-500 rounded-full"
                              style={{ width: `${allocation}%` }}
                            />
                          </div>
                          <span className="text-xs font-mono text-primary-400 w-8 text-right">
                            {allocation.toFixed(1)}%
                          </span>
                        </div>
                      </td>
                    </tr>
                  );
                })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Portfolio;
