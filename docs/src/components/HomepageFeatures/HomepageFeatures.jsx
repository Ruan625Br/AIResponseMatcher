import React from "react";
import styles from "./HomepageFeatures.module.css";

const FeatureList = [
  {
    title: "Easy to Use",
    description: (
      <>
        Simplifying the integration process, the library is designed with
        user-friendliness in mind. Its straightforward and intuitive API makes
        it easy for developers to seamlessly incorporate and utilize its
        features in their projects.
      </>
    ),
  },
  {
    title: "Cross-Platform Compatibility",
    description: (
      <>
        Migrated for compatibility with all JVM-based platforms, the library now
        extends its functionality beyond Android. It can be seamlessly employed
        across various Java-based environments, ensuring a broader reach and
        enhanced usability.
      </>
    ),
  },
  {
    title: "Configurability",
    description: (
      <>
        The library provides a high degree of configurability, allowing
        developers to tailor its settings according to their project
        requirements. This flexibility ensures adaptability to a variety of use
        cases and scenarios.
      </>
    ),
  },
];

function Feature({ title, description }) {
  return (
    <div className={styles.feature}>
      <h3 className={styles.title}>{title}</h3>
      <p>{description}</p>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.featuresSection}>
      <div className={styles.featuresContainer}>
        {FeatureList.map((props, idx) => (
          <Feature key={idx} {...props} />
        ))}
      </div>
    </section>
  );
}
