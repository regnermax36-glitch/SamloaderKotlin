import { useState, useEffect, useCallback } from 'react';

const DEFAULT_ENDPOINT = 'https://api.exchangerate-api.com/v4/latest/USD';
const STORAGE_KEY = 'currency_api_endpoint';

export interface ExchangeRates {
  [key: string]: number;
}

export const useCurrency = () => {
  const [rates, setRates] = useState<ExchangeRates>({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [endpoint, setEndpointState] = useState<string>(
    localStorage.getItem(STORAGE_KEY) || DEFAULT_ENDPOINT
  );

  const fetchRates = useCallback(async (url: string) => {
    try {
      setLoading(true);
      setError(null);
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error(`Failed to fetch rates: ${response.statusText}`);
      }
      const data = await response.json();
      if (data && data.rates) {
        setRates(data.rates);
      } else {
        throw new Error('Invalid data format received from API');
      }
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An unknown error occurred');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchRates(endpoint);
  }, [endpoint, fetchRates]);

  const updateEndpoint = (newEndpoint: string) => {
    const url = newEndpoint || DEFAULT_ENDPOINT;
    localStorage.setItem(STORAGE_KEY, url);
    setEndpointState(url);
  };

  return {
    rates,
    loading,
    error,
    endpoint,
    setEndpoint: updateEndpoint,
    refresh: () => fetchRates(endpoint),
  };
};
