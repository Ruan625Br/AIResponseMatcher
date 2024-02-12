import React from "react";
import Layout from "@theme/Layout";
import useDocusaurusContext from "@docusaurus/useDocusaurusContext";
import HomepageFeatures from "../components/HomepageFeatures/HomepageFeatures";
import HomepageHero from "../components/HomepageHero/HomepageHero";
import CallToAction from "../components/CallToAction/CallToAction";
import styles from "./index.module.css";

export default function Home() {
  const { siteConfig } = useDocusaurusContext();
  return (
    <Layout
      title={`${siteConfig.title}`}
      description="Simplify the analysis of AI output and invocation of corresponding operations with AIResponseMatcher."
    >
      <div className={styles.container}>
        <HomepageHero />
        <HomepageFeatures />
        <CallToAction />
      </div>
    </Layout>
  );
}
