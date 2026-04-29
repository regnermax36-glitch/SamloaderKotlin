import { describe, it, expect, vi, beforeEach } from 'vitest';
import { renderHook, waitFor, act } from '@testing-library/react';
import { useCurrency } from './useCurrency';

// Mock fetch
const mockRates = {
  USD: 1,
  EUR: 0.85,
  GBP: 0.75,
};

(globalThis as any).fetch = vi.fn();

describe('useCurrency', () => {
  beforeEach(() => {
    vi.clearAllMocks();
    localStorage.clear();
    (fetch as any).mockResolvedValue({
      ok: true,
      json: () => Promise.resolve({ rates: mockRates }),
    });
  });

  it('should fetch rates on mount', async () => {
    const { result } = renderHook(() => useCurrency());

    expect(result.current.loading).toBe(true);

    await waitFor(() => {
      expect(result.current.loading).toBe(false);
    });

    expect(result.current.rates).toEqual(mockRates);
    expect(result.current.error).toBeNull();
  });

  it('should handle fetch error', async () => {
    (fetch as any).mockResolvedValue({
      ok: false,
      statusText: 'Not Found',
    });

    const { result } = renderHook(() => useCurrency());

    await waitFor(() => {
      expect(result.current.loading).toBe(false);
    });

    expect(result.current.error).toContain('Failed to fetch rates');
  });

  it('should update endpoint and persist to localStorage', async () => {
    const { result } = renderHook(() => useCurrency());
    const newEndpoint = 'https://api.test.com/latest';

    await waitFor(() => {
      expect(result.current.loading).toBe(false);
    });

    act(() => {
      result.current.setEndpoint(newEndpoint);
    });

    expect(result.current.endpoint).toBe(newEndpoint);
    expect(localStorage.getItem('currency_api_endpoint')).toBe(newEndpoint);

    await waitFor(() => {
      expect(fetch).toHaveBeenCalledWith(newEndpoint);
    });
  });
});
