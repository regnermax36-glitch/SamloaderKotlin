import { useState, useEffect, useCallback } from 'react';

export interface Transaction {
  id: string;
  type: 'BUY' | 'SELL';
  fromCurrency: string;
  toCurrency: string;
  fromAmount: number;
  toAmount: number;
  rate: number;
  timestamp: number;
}

export interface PortfolioHoldings {
  [key: string]: number;
}

const STORAGE_KEY = 'bifrost_portfolio';
const INITIAL_BALANCE = 10000;

export const usePortfolio = () => {
  const [balance, setBalance] = useState<number>(INITIAL_BALANCE);
  const [holdings, setHoldings] = useState<PortfolioHoldings>({ USD: INITIAL_BALANCE });
  const [history, setHistory] = useState<Transaction[]>([]);

  // Load from localStorage
  useEffect(() => {
    const saved = localStorage.getItem(STORAGE_KEY);
    if (saved) {
      try {
        const parsed = JSON.parse(saved);
        setBalance(parsed.balance ?? INITIAL_BALANCE);
        setHoldings(parsed.holdings ?? { USD: INITIAL_BALANCE });
        setHistory(parsed.history ?? []);
      } catch (e) {
        console.error('Failed to parse portfolio data', e);
      }
    }
  }, []);

  // Save to localStorage
  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify({ balance, holdings, history }));
  }, [balance, holdings, history]);

  const executeTrade = useCallback((
    type: 'BUY' | 'SELL',
    fromCurr: string,
    toCurr: string,
    amount: number, // amount of fromCurr
    rate: number
  ) => {
    const fromBalance = holdings[fromCurr] || 0;
    if (fromBalance < amount) {
      throw new Error(`Insufficient ${fromCurr} balance`);
    }

    const receiveAmount = amount * rate;

    const newHoldings = { ...holdings };
    newHoldings[fromCurr] = fromBalance - amount;
    newHoldings[toCurr] = (newHoldings[toCurr] || 0) + receiveAmount;

    const newTransaction: Transaction = {
      id: Math.random().toString(36).substr(2, 9),
      type,
      fromCurrency: fromCurr,
      toCurrency: toCurr,
      fromAmount: amount,
      toAmount: receiveAmount,
      rate,
      timestamp: Date.now(),
    };

    setHoldings(newHoldings);
    setHistory(prev => [newTransaction, ...prev]);

    if (fromCurr === 'USD') setBalance(prev => prev - amount);
    if (toCurr === 'USD') setBalance(prev => prev + receiveAmount);

    return true;
  }, [holdings]);

  const resetPortfolio = () => {
    setBalance(INITIAL_BALANCE);
    setHoldings({ USD: INITIAL_BALANCE });
    setHistory([]);
  };

  return {
    balance,
    holdings,
    history,
    executeTrade,
    resetPortfolio,
  };
};
