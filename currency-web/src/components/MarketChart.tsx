import React, { useMemo } from 'react';
import { ResponsiveContainer, AreaChart, Area, XAxis, YAxis, Tooltip, CartesianGrid } from 'recharts';

interface MarketChartProps {
  currency: string;
  baseRate: number;
}

const MarketChart: React.FC<MarketChartProps> = ({ currency, baseRate }) => {
  const data = useMemo(() => {
    const points = [];
    let currentRate = baseRate;
    const now = new Date();

    for (let i = 24; i >= 0; i--) {
      const time = new Date(now.getTime() - i * 60 * 60 * 1000);
      // Simulate random walk
      currentRate = currentRate * (1 + (Math.random() * 0.04 - 0.02));
      points.push({
        time: time.toLocaleTimeString('de-DE', { hour: '2-digit', minute: '2-digit' }),
        rate: parseFloat(currentRate.toFixed(4)),
      });
    }
    return points;
  }, [baseRate]);

  return (
    <div className="glass-card w-full h-[400px]">
      <div className="flex justify-between items-center mb-6">
        <div>
          <h3 className="text-lg font-bold">USD / {currency}</h3>
          <p className="text-slate-400 text-xs">Simulierter 24-Stunden-Kursverlauf</p>
        </div>
        <div className="text-right">
          <div className="text-xl font-mono font-bold text-primary-400">{baseRate.toFixed(4)}</div>
          <div className="text-emerald-400 text-xs">+0.42%</div>
        </div>
      </div>

      <ResponsiveContainer width="100%" height="80%">
        <AreaChart data={data}>
          <defs>
            <linearGradient id="colorRate" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#0ea5e9" stopOpacity={0.3}/>
              <stop offset="95%" stopColor="#0ea5e9" stopOpacity={0}/>
            </linearGradient>
          </defs>
          <CartesianGrid strokeDasharray="3 3" stroke="#ffffff10" vertical={false} />
          <XAxis
            dataKey="time"
            stroke="#94a3b8"
            fontSize={10}
            tickLine={false}
            axisLine={false}
            interval={4}
          />
          <YAxis
            hide
            domain={['auto', 'auto']}
          />
          <Tooltip
            contentStyle={{
              backgroundColor: '#1e293b',
              border: '1px solid #334155',
              borderRadius: '12px',
              color: '#f8fafc'
            }}
            itemStyle={{ color: '#0ea5e9' }}
          />
          <Area
            type="monotone"
            dataKey="rate"
            stroke="#0ea5e9"
            strokeWidth={3}
            fillOpacity={1}
            fill="url(#colorRate)"
            animationDuration={1500}
          />
        </AreaChart>
      </ResponsiveContainer>
    </div>
  );
};

export default MarketChart;
