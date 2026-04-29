import { useState } from 'react';
import Header from './components/Header';
import Converter from './components/Converter';
import RateList from './components/RateList';
import Settings from './components/Settings';
import TradingTerminal from './components/TradingTerminal';
import Portfolio from './components/Portfolio';
import TransactionHistory from './components/TransactionHistory';
import MarketChart from './components/MarketChart';
import { useCurrency } from './hooks/useCurrency';
import { usePortfolio } from './hooks/usePortfolio';
import { Loader2, AlertCircle, LineChart, Repeat, Briefcase, History as HistoryIcon } from 'lucide-react';

type Tab = 'market' | 'trade' | 'portfolio' | 'history';

function App() {
  const { rates, loading, error, endpoint, setEndpoint, refresh } = useCurrency();
  const { holdings, history, executeTrade } = usePortfolio();
  const [isSettingsOpen, setIsSettingsOpen] = useState(false);
  const [activeTab, setActiveTab] = useState<Tab>('market');

  const navItems = [
    { id: 'market', label: 'Markt', icon: LineChart },
    { id: 'trade', label: 'Handel', icon: Repeat },
    { id: 'portfolio', label: 'Portfolio', icon: Briefcase },
    { id: 'history', label: 'Verlauf', icon: HistoryIcon },
  ];

  return (
    <div className="min-h-screen w-full flex flex-col pb-12">
      <Header onSettingsClick={() => setIsSettingsOpen(true)} />

      <main className="flex-1 px-6 max-w-7xl mx-auto w-full">
        {/* Navigation Tabs */}
        <div className="flex gap-2 mb-8 bg-white/5 p-1.5 rounded-2xl w-fit mx-auto sm:mx-0">
          {navItems.map((item) => (
            <button
              key={item.id}
              onClick={() => setActiveTab(item.id as Tab)}
              className={`flex items-center gap-2 px-4 py-2 rounded-xl text-sm font-semibold transition-all ${activeTab === item.id ? 'bg-primary-500 text-white shadow-lg shadow-primary-500/20' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
            >
              <item.icon className="w-4 h-4" />
              <span className="hidden sm:inline">{item.label}</span>
            </button>
          ))}
        </div>

        {loading && !Object.keys(rates).length ? (
          <div className="h-[50vh] flex flex-col items-center justify-center gap-4">
            <Loader2 className="w-12 h-12 text-primary-400 animate-spin" />
            <p className="text-slate-400 font-medium">Terminaldaten werden geladen...</p>
          </div>
        ) : error ? (
          <div className="glass-card max-w-md mx-auto mt-12 border-rose-500/20 bg-rose-500/5">
            <div className="flex items-center gap-3 text-rose-400 mb-4">
              <AlertCircle className="w-6 h-6" />
              <h2 className="font-bold">System Offline</h2>
            </div>
            <p className="text-slate-300 text-sm mb-6">{error}</p>
            <button onClick={refresh} className="w-full btn-primary !from-rose-500 !to-orange-500">Verbindung erneut versuchen</button>
          </div>
        ) : (
          <div className="animate-in fade-in slide-in-from-bottom-4 duration-500">
            {activeTab === 'market' && (
              <div className="space-y-8">
                <div className="grid grid-cols-1 lg:grid-cols-[1fr_350px] gap-8">
                  <MarketChart currency="EUR" baseRate={rates['EUR']} />
                  <Converter rates={rates} />
                </div>
                <RateList rates={rates} />
              </div>
            )}

            {activeTab === 'trade' && (
              <div className="max-w-4xl mx-auto py-8">
                 <div className="text-center mb-12">
                   <h2 className="text-3xl font-bold mb-2">Institutioneller Handel</h2>
                   <p className="text-slate-400">Führen Sie Währungsgeschäfte mit sofortiger Abrechnung aus.</p>
                 </div>
                 <TradingTerminal rates={rates} holdings={holdings} onTrade={executeTrade} />
              </div>
            )}

            {activeTab === 'portfolio' && (
              <Portfolio holdings={holdings} rates={rates} />
            )}

            {activeTab === 'history' && (
              <TransactionHistory history={history} />
            )}
          </div>
        )}
      </main>

      <Settings
        isOpen={isSettingsOpen}
        onClose={() => setIsSettingsOpen(false)}
        currentEndpoint={endpoint}
        onSave={setEndpoint}
      />

      <footer className="mt-12 text-center text-slate-500 text-xs">
        <p>© 2026 Bifrost FX Terminal. Virtuelle Börse für institutionelle Ansprüche.</p>
      </footer>
    </div>
  );
}

export default App;
