/** @type {import('next').NextConfig} */
const nextConfig = {
  experimental: {
    appDir: true,
  },
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'www.congress.gov',
        port: '',
        pathname: '/**',
      },
    ],
  },
}

module.exports = nextConfig
