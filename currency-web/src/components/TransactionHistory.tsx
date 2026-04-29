import React from 'react';
import { History, ArrowUpRight, ArrowDownLeft } from 'lucide-react';
import type { Transaction } from '../hooks/usePortfolio';

interface TransactionHistoryProps {
  history: Transaction[];
}

const TransactionHistory: React.FC<TransactionHistoryProps> = ({ history }) => {
  if (history.length === 0) {
    return (
      <div className="glass-card text-center py-12">
        <History className="w-12 h-12 text-slate-600 mx-auto mb-4" />
        <h3 className="text-xl font-bold text-slate-400">Noch keine Transaktionen</h3>
        <p className="text-slate-500 text-sm">Ihre Handelsaktivitäten werden hier angezeigt.</p>
      </div>
    );
  }

  return (
    <div className="glass-card overflow-hidden">
      <div className="flex items-center gap-3 mb-6">
        <History className="w-6 h-6 text-primary-400" />
        <h2 className="text-xl font-bold">Transaktionsverlauf</h2>
      </div>

      <div className="overflow-x-auto">
        <table className="w-full">
          <thead>
            <tr className="text-left text-xs text-slate-400 uppercase tracking-widest border-b border-white/5">
              <th className="pb-4 font-bold">Zeitpunkt</th>
              <th className="pb-4 font-bold">Typ</th>
              <th className="pb-4 font-bold">Paar</th>
              <th className="pb-4 font-bold text-right">Betrag</th>
              <th className="pb-4 font-bold text-right">Erhalten</th>
              <th className="pb-4 font-bold text-right">Kurs</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-white/5">
            {history.map((tx) => (
              <tr key={tx.id} className="group hover:bg-white/5 transition-colors">
                <td className="py-4 text-sm text-slate-400">
                  {new Date(tx.timestamp).toLocaleString('de-DE', { month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' })}
                </td>
                <td className="py-4">
                  <span className={`flex items-center gap-1.5 text-xs font-bold px-2.5 py-1 rounded-full w-fit ${tx.type === 'BUY' ? 'bg-emerald-500/10 text-emerald-400' : 'bg-rose-500/10 text-rose-400'}`}>
                    {tx.type === 'BUY' ? <ArrowUpRight className="w-3 h-3" /> : <ArrowDownLeft className="w-3 h-3" />}
                    {tx.type === 'BUY' ? 'KAUF' : 'VERKAUF'}
                  </span>
                </td>
                <td className="py-4 font-semibold text-slate-200">
                  {tx.fromCurrency} / {tx.toCurrency}
                </td>
                <td className="py-4 text-right font-mono text-slate-300">
                  {tx.fromAmount.toLocaleString('de-DE', { minimumFractionDigits: 2 })} {tx.fromCurrency}
                </td>
                <td className="py-4 text-right font-mono text-white">
                  {tx.toAmount.toLocaleString('de-DE', { minimumFractionDigits: 2 })} {tx.toCurrency}
                </td>
                <td className="py-4 text-right font-mono text-primary-400">
                  {tx.rate.toFixed(4)}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default TransactionHistory;
