// @ts-check
// `@type` JSDoc annotations allow editor autocompletion and type checking
// (when paired with `@ts-check`).
// There are various equivalent ways to declare your Docusaurus config.
// See: https://docusaurus.io/docs/api/docusaurus-config

import { themes as prismThemes } from "prism-react-renderer";

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: "AIResponseMatcher",
  tagline:
    "A small library that analyzes the output of an AI and performs corresponding operations based on that analysis.",
  url: "https://ruan625Br.github.io",
  baseUrl: "/AIResponseMatcher/",
  organizationName: "ruan625Br", // Usually your GitHub org/user name.
  projectName: "AIResponseMatcher", // Usually your repo name.
  onBrokenLinks: "throw",
  onBrokenMarkdownLinks: "warn",

  // Even if you don't use internationalization, you can use this field to set
  // useful metadata like html lang. For example, if your site is Chinese, you
  // may want to replace "en" with "zh-Hans".
  i18n: {
    defaultLocale: "en",
    locales: ["en"],
  },

  presets: [
    [
      "classic",
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: "./sidebars.js",
          editUrl: "https://ruan625br/AIResponseMatcher/tree/master/docs",
        },
        theme: {
          customCss: require.resolve("./src/css/custom.css"),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      colorMode: {
        defaultMode: 'dark',
        disableSwitch: true
      },
      navbar: {
        title: "AIResponseMatcher",
        hideOnScroll: true,
        items: [
          {
            type: "doc",
            docId: "intro",
            position: "left",
            label: "docs",
          },
          {
            href: "https://github.com/Ruan625Br/AIResponseMatcher",
            label: "GitHub",
            position: "right",
          },
        ],
      },
      footer: {
        style: "light",
        links: [
          {
            title: "docs",
            items: [
              {
                label: "installation",
                to: "/docs/introduction/",
              },
              {
                label: "usage",
                to: "/docs/introduction/usage",
              },
              {
                label: "functions",
                to: "/docs/functions/",
              },
              {
                label: "contributing",
                to: "/docs/contributing",
              },
            ],
          },
          {
            title: "More",
            items: [
              {
                label: "GitHub",
                href: "https://github.com/Ruan625Br/AIResponseMatcher",
              },
              {
                label: "buy me a coffee",
                href: "https://ko-fi.com/juannascimento",
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} AIResponseMatcher docs`,
      },
      prism: {
        theme: prismThemes.github,
        darkTheme: prismThemes.dracula,
      },
    }),
};

export default config;
