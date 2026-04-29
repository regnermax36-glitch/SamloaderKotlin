import { useState } from 'react';
import Header from './components/Header';
import Converter from './components/Converter';
import RateList from './components/RateList';
import Settings from './components/Settings';
import { useCurrency } from './hooks/useCurrency';
import { Loader2, AlertCircle } from 'lucide-react';

function App() {
  const { rates, loading, error, endpoint, setEndpoint, refresh } = useCurrency();
  const [isSettingsOpen, setIsSettingsOpen] = useState(false);

  return (
    <div className="min-h-screen w-full flex flex-col pb-12">
      <Header onSettingsClick={() => setIsSettingsOpen(true)} />

      <main className="flex-1 px-6 max-w-7xl mx-auto w-full">
        {loading && !Object.keys(rates).length ? (
          <div className="h-[60vh] flex flex-col items-center justify-center gap-4">
            <Loader2 className="w-12 h-12 text-primary-400 animate-spin" />
            <p className="text-slate-400 font-medium">Fetching latest market rates...</p>
          </div>
        ) : error ? (
          <div className="glass-card max-w-md mx-auto mt-12 border-rose-500/20 bg-rose-500/5">
            <div className="flex items-center gap-3 text-rose-400 mb-4">
              <AlertCircle className="w-6 h-6" />
              <h2 className="font-bold">Connection Error</h2>
            </div>
            <p className="text-slate-300 text-sm mb-6">{error}</p>
            <button
              onClick={refresh}
              className="w-full btn-primary !from-rose-500 !to-orange-500"
            >
              Try Again
            </button>
          </div>
        ) : (
          <div className="space-y-8 animate-in fade-in slide-in-from-bottom-4 duration-700">
            <section>
              <Converter rates={rates} />
            </section>

            <section>
              <RateList rates={rates} />
            </section>
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
        <p>© 2026 Bifrost FX. Powered by modern Open APIs.</p>
      </footer>
    </div>
  );
}

export default App;
